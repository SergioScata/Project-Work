

document.getElementById("quantita").addEventListener("change", totalCalc);

function totalCalc() {
  let nProdotti = document.getElementById("quantita").value;
  nProdotti = parseInt(nProdotti);
  console.log(nProdotti);

  let prezzoSingolo = document.getElementById("prezzoSingolo").getAttribute("th:value");
   console.log(prezzoSingolo);
  prezzoSingolo = parseFloat(prezzoSingolo);
  console.log(prezzoSingolo);

  let prezzoTotale = nProdotti*1;
  prezzoTotale = prezzoTotale.toFixed(2);
  console.log(prezzoTotale);
  document.getElementById("totalPrice").innerHTML=`${prezzoTotale} €`
}