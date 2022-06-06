var estimate = 0;

const computePrice = () => {
  var price = 0;

  switch ($("#commType").val()) {
    case "PETITE":
      price += 20;
      break;
    case "MOYENNE":
      price += 35;
      break;
    case "GRANDE":
      price += 50;
      break;
    case "QUAD":
      price += 35;
      break;
    case "AUTRE":
      estimate = 0;
      return "Pas d'estimation";
  }

  price += 5 * parseInt($("#commAccs").val());

  if($("#commAnat").is(":checked")) {price += 10;}

  if($("#commYeux").is(":checked")) {price += 5;}

  estimate = price;
  return price + "€";
}

const postComm = (id) => {
  var comm = {
    "titre": $("#commTitle").val(),
    "type": $("#commType").val(),
    "prix": estimate,
    "statut": "DEMANDE",
    "client": {"id": id}
  };

  $.ajax({
    method: "post",
    url: "../rest/commissions",
    dataType : "json",
    contentType: "application/json; charset=utf-8",
    data : JSON.stringify(comm)
  });
}


$(function() {

  console.log("test log");

  //estimation initiale du prix
  $("#commPrice").append(computePrice());

  //re-calcule le prix à chaque changement du formulaire
  $(".form").change(function() {
    $("#commPrice").empty();
    $("#commPrice").append(computePrice());
  });

  //affiche le nom du client
  $("#clientIdInput").change(function() {

    $.get("../rest/clients/" + $("#clientIdInput").val(), function(data, status) {
      
      $("#clientName").empty();
      if (status == "success") {$("#clientName").append(data.nom);}
      else {$("#clientName").append("Identifiant invalide.");}

    });
  });

  //affiche la section correcte
  $("#newClientCheckbox").change(function() {
    if (this.checked) {
      $("#oldClient").hide();
      $("#newClient").show();
    } else {
      $("#oldClient").show();
      $("#newClient").hide();
    }
  });

  //crée la commission et éventuellement le nouveau client
  $("#createCommButton").on("click", function() {

    var idClient = null;

    if ($("#newClientCheckbox").is(":checked")) { //création client

      var clientData = {
        "nom": $("#clientNameInput").val(),
        "contacts": $("#clientContactsInput").val()
      };
  
      $.ajax({
        method: "post",
        url: "../rest/clients",
        dataType : "json",
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify(clientData),
        success: function(result) {
          console.log(result); 
          idClient = result.id;

          postComm(idClient);

        }
      });

    } else {
      idClient = $("#clientIdInput").val();

      postComm(idClient);

    }

    

    $("#confirmationOutput").empty();
    $("#confirmationOutput").append("La demande a été envoyée !")

  });
  
});