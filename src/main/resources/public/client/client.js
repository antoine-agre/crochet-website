const renderComm = (id, titre, type, prix, statut) => {
  return `<h3>${titre}</h3>
  <p><strong>Identifiant : </strong>${id}</p>
  <p><strong>Type : </strong>${type}</p>
  <p><strong>Prix : </strong>${prix}€</p>
  <p><strong>Statut : </strong>${statut}</p>
  <hr>`
}

$(function() {

  console.log("test log");

  $("#clientNumberButton").on("click", function() { //vérifier si entier rentré
    var number = $("#clientNumberInput").val();
    console.log("Number : " + number);

    $.get("../rest/commissions?idClient=" + number, function(data, status) {
      $("#listeComms").empty();
      data.forEach((el) => {
        $("#listeComms").append(renderComm(el.id, el.titre, el.type, el.prix, el.statut))
      })
    });

  });

  /*$.get("rest/personnes", function(data, status) {
    alert("requête : " + status);
    data.forEach((el) => {
      $("#listePersonnes").append(renderEl(el.id, el.prenom, el.nom, el.age, el.mail));
    })
  });*/
  
});