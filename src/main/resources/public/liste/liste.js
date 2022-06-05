const renderComm = (id, titre, type, prix, statut, client) => {
  return `<h3>${titre}</h3>
  <p><strong>Identifiant : </strong>${id}</p>
  <p><strong>Client : </strong>${client.nom}</p>
  <p><strong>Type : </strong>${type}</p>
  <p><strong>Prix : </strong>${prix}€</p>
  <p><strong>Statut : </strong>${statut}</p>
  <hr>`
}

$(function() {

  console.log("test log");

  $.get("../rest/commissions?active=true", function(data, status) {
    data.forEach((el) => {
      $("#listeComms").append(renderComm(el.id, el.titre, el.type, el.prix, el.statut, el.client));
    });
  });

  /*$.get("rest/personnes", function(data, status) {
    alert("requête : " + status);
    data.forEach((el) => {
      $("#listePersonnes").append(renderEl(el.id, el.prenom, el.nom, el.age, el.mail));
    })
  });*/
  
});