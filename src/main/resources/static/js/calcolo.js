

document.getElementById("quantita").addEventListener("change", totalCalc);

function totalCalc() {
  let nProdotti = document.getElementById("quantita").value;
  nProdotti = parseInt(nProdotti);
  console.log(nProdotti);

  let prezzoSingolo = document.getElementById("prezzoSingolo").textContent;
   console.log(prezzoSingolo);
  prezzoSingolo = parseFloat(prezzoSingolo);
  console.log(prezzoSingolo);

  let prezzoTotale = nProdotti*prezzoSingolo;
  prezzoTotale = prezzoTotale.toFixed(2);
  console.log(prezzoTotale);
  document.getElementById("totalPrice").innerHTML=`${prezzoTotale} €`
}