

document.getElementById("quantita").addEventListener("change", totalCalc);

function totalCalc() {
  let nProdotti = document.getElementById("quantita").value;
  nProdotti = parseInt(nProdotti);

  let prezzoSingolo = document.getElementById("prezzoSingolo").textContent;
  prezzoSingolo = parseFloat(prezzoSingolo);

  let prezzoTotale = nProdotti*prezzoSingolo;
  prezzoTotale = prezzoTotale.toFixed(2);
  document.getElementById("totalPrice").innerHTML=`${prezzoTotale} €`
}