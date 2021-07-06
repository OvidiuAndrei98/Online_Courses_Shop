let button = document.querySelectorAll(".btn-success");
let itemCount = document.querySelector(".itemCount");
let cart = document.querySelector(".bi-cart");

// Get the modal
let modal = document.getElementById("myModal");

// Get the button that opens the modal
let btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
let span = document.getElementsByClassName("close")[0];


async function loadData(e) {
    e.preventDefault();
    let API = "/cart?productId=" + e.target.dataset.id;
    let request = await fetch(API);
    let response = await request.json();
    itemCount.innerHTML = response.quantity;
}

button.forEach(btn => {
    btn.addEventListener("click",loadData)
});


async function loadCart(e) {
    e.preventDefault();
    modal.style.display = "block";



}

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

cart.addEventListener("click",loadCart);