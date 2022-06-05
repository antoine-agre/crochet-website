const renderComm = (id, titre, type, prix, statut, client) => {
  return `<h3>${titre}</h3>
  <p><strong>Identifiant : </strong>${id}</p>
  <p><strong>Client : </strong>${client.nom}</p>
  <p><strong>Type : </strong>${type}</p>
  <p><strong>Prix : </strong>${prix}€</p>
  <p><strong>Statut : </strong>${statut}</p>
  <hr>`
}

const renderCurrentComm = (id, titre, type, prix, statut, client) => {
  return `<p><strong>Identifiant : </strong>${id}</p>
  <p><strong>Client : </strong>${client.nom}</p>
  <p><strong>Titre : </strong><input type="text" id="titleInput" value="${titre}"></p>
  <p><strong>Type : </strong><select id="typeDropdown" >
    <option ${type == "PETITE" ? "selected" : ""} value="PETITE">Petite</option>
    <option ${type == "MOYENNE" ? "selected" : ""} value="MOYENNE">Moyenne</option>
    <option ${type == "GRANDE" ? "selected" : ""} value="GRANDE">Grande</option>
    <option ${type == "QUAD" ? "selected" : ""} value="QUAD">Quadrupède</option>
    <option ${type == "AUTRE" ? "selected" : ""} value="AUTRE">Autre</option></select></p>
  <p><strong>Prix : </strong><input type="number" id="priceInput" value="${prix}"> €</p>
  <p><strong>Statut : </strong><select id="statusDropdown">
    <option ${statut == "DEMANDE" ? "selected" : ""} value="DEMANDE">Demande</option>
    <option ${statut == "DISCUSSION" ? "selected" : ""} value="DISCUSSION">Discussion</option>
    <option ${statut == "ATTENTE_PAIEMENT" ? "selected" : ""} value="ATTENTE_PAIEMENT">En attente de paiement</option>
    <option ${statut == "PAYEE" ? "selected" : ""} value="PAYEE">Payée</option>
    <option ${statut == "FINIE" ? "selected" : ""} value="FINIE">Finie</option></select></p>`
}

var currentCommId = null;


$(function() {

  console.log("test log");

  //charger commission de la BdD
  $("#commIdButton").on("click", function() {
    $.get("../rest/commissions/" + $("#commIdInput").val(), function(data, status) {
      $("#currentComm").empty();
      if(status == "success") {
        $("#currentComm").append(renderCurrentComm(data.id, data.titre, data.type, data.prix, data.statut, data.client));
        currentCommId = data.id;
      } else {$("#currentComm").append("Cet identifiant n'est pas valide.");}
    });
  });

  //Sauvegarder les modifications
  $("#currentCommButton").on("click", function() {
    
    var patch = {
      "titre": $("#titleInput").val(),
      "type": $("#typeDropdown").val(),
      "prix": $("#priceInput").val(),
      "statut": $("#statusDropdown").val()
    };
    
    $.ajax({
      type: "PATCH",
      url: "../rest/commissions/" + currentCommId, //$("#currentCommId").val(),
      data: JSON.stringify(patch),
      processData: false,
      contentType: "application/merge-patch+json"
    });

    $("#currentComm").append("Commission mise à jour.");
  });

  //Supprimer la commission
  $("#currentCommDelete").on("click", function() {
    
    $.ajax({
      type: "DELETE",
      url: "../rest/commissions/" + currentCommId
    });

    $("#currentComm").empty();
    $("#currentComm").append("Commission supprimée.");
  });

  //remplir liste de commissions
  $.get("../rest/commissions", function(data, status) {
    data.forEach((el) => {
      $("#listeComms").append(renderComm(el.id, el.titre, el.type, el.prix, el.statut, el.client));
    });
  });
  
});