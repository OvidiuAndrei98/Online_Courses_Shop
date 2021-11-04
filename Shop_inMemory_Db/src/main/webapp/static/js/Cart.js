let button = document.querySelectorAll(".btn-success");
let itemCount = document.querySelector(".itemCount");
let cart = document.querySelector(".bi-cart");
let modalItems = document.querySelector("#modal-items");
let checkoutModal = document.querySelector(".form-container");
let plusButton = document.getElementById("plus");
let minusButton = document.querySelector(".minus");


// Get the modal
let modal = document.getElementById("myModal");

// Get the button that opens the modal
let btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
let span = document.getElementsByClassName("close")[0];


async function loadData(e) {
    console.log("YES")
    e.preventDefault();
     let API = "/cart?productId=" + e.target.dataset.id;
     console.log(e.target)
    let request = await fetch(API);
    let response = await request.json();
    itemCount.innerHTML = response.quantity;
    let products = JSON.parse(response.products)
    modalItems.innerHTML = "";
    checkoutModal.innerHTML = "";
    products.forEach((product) => {
        console.log(product.product)
        modalItems.innerHTML += `
                <div class="col-5">
                    <div class="row d-flex">
                        <div class="book"><img src="/static/img/product_${product.itemId}.png" class="book-img"/></div>
                        <div class="my-auto flex-column d-flex pad-left">
                            <h6 class="mob-text">${product.product.name}</h6>
                            <p class="mob-text">${product.product.description}</p>
                        </div>
                    </div>
                </div>
                <div class="my-auto col-7">
                    <div class="row text-right">
                        <div class="col-4">
                            <p class="mob-text">Digital</p>
                        </div>
                        <div class="col-4">
                            <div class="row d-flex justify-content-end px-3">
                                <p class="mb-0" id="cnt1" >${product.quantity}</p>
                                <div class="d-flex flex-column plus-minus"> <span class="vsm-text plus" id="plus" data-action="update" data-productId="${product.product.id}">+</span> <span data-productId="${product.product.id}" class="vsm-text minus" data-action="update">-</a> </div>
                            </div>
                        </div>
                        <div class="col-4">
                            <h6 class="mob-text">${product.unitPrice * product.quantity}$</h6>
                        </div>
                    </div>
                </div>
            `
    })
    checkoutModal.innerHTML = ` <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="firstName">First name</label>
                                            <input type="text" class="form-control" id="firstName" placeholder=""
                                                   value="" required="" name="firstname">
                                            <div class="invalid-feedback"> Valid first name is required.</div>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="lastName">Last name</label>
                                            <input type="text" class="form-control" id="lastName" placeholder=""
                                                   value="" required="" name="lastname">
                                            <div class="invalid-feedback"> Valid last name is required.</div>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="username">Username</label>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">@</span>
                                            </div>
                                            <input type="text" class="form-control" id="username" placeholder="Username"
                                                   required="" name="username">
                                            <div class="invalid-feedback" style="width: 100%;"> Your username is
                                                required.
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="email">Email <span class="text-muted">(Optional)</span></label>
                                        <input type="email" class="form-control" id="email"
                                               placeholder="you@example.com" name="email">
                                        <div class="invalid-feedback"> Please enter a valid email address for shipping
                                            updates.
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="address">Address</label>
                                        <input type="text" class="form-control" id="address" placeholder="1234 Main St"
                                               required="" name="address">
                                        <div class="invalid-feedback"> Please enter your shipping address.</div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="address2">Address 2 <span
                                                class="text-muted">(Optional)</span></label>
                                        <input type="text" class="form-control" id="address2"
                                               placeholder="Apartment or suite" name="address2">
                                    </div>
                                    <div class="row">
                                        <div class="col-md-5 mb-3">
                                            <label for="country">Country</label>
                                            <select class="custom-select d-block w-100" id="country" required="" name="country">
                                                <option value="">Choose...</option>
                                                <option>United States</option>
                                            </select>
                                            <div class="invalid-feedback"> Please select a valid country.</div>
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <label for="state">State</label>
                                            <select class="custom-select d-block w-100" id="state" required="" name="state">
                                                <option value="">Choose...</option>
                                                <option>California</option>
                                            </select>
                                            <div class="invalid-feedback"> Please provide a valid state.</div>
                                        </div>
                                        <div class="col-md-3 mb-3">
                                            <label for="zip">Zip</label>
                                            <input type="text" class="form-control" id="zip" placeholder="" required="" name="zip">
                                            <div class="invalid-feedback"> Zip code required.</div>
                                        </div>
                                    </div>
                                    <hr class="mb-4">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="same-address" name="sameaddress">
                                        <label class="custom-control-label" for="same-address">Shipping address is the
                                            same as my billing address</label>
                                    </div>
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="save-info" name="saveinfo">
                                        <label class="custom-control-label" for="save-info">Save this information for
                                            next time</label>
                                    </div>
                                    <hr class="mb-4">
                                    <hr class="mb-4">
                                    <div class="d-flex align-items-start">
                                        <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist"
                                             aria-orientation="vertical">
                                            <button class="nav-link active" id="v-pills-home-tab" data-bs-toggle="pill"
                                                    data-bs-target="#v-pills-home" type="button" role="tab" aria-controls="v-pills-home"
                                                    aria-selected="true"><img class="pay" src="https://i.imgur.com/OdxcctP.jpg">
                                            </button>
                                            <button class="nav-link" id="v-pills-profile-tab" data-bs-toggle="pill"
                                                    data-bs-target="#v-pills-profile" type="button" role="tab"
                                                    aria-controls="v-pills-profile" aria-selected="false"><img class="pay"
                                                                                                               src="https://i.imgur.com/cMk1MtK.jpg">
                                            </button>
                                        </div>
                                        <div class="tab-content" id="v-pills-tabContent">
                                            <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
                                                 aria-labelledby="v-pills-home-tab">
                                                <div class="row">
                                                    <div class="col-lg-5">
                                                        <div class="row px-2">
                                                            <div class="form-group col-md-6"><label class="form-control-label">Name
                                                                on Card</label> <input type="text" id="cname" name="cname"
                                                                                       placeholder="Johnny Doe"></div>
                                                            <div class="form-group col-md-6"><label class="form-control-label">Card
                                                                Number</label> <input type="text" id="cnum" name="cnum"
                                                                                      placeholder="1111 2222 3333 4444"></div>
                                                        </div>
                                                        <div class="row px-2">
                                                            <div class="form-group col-md-6"><label class="form-control-label">Expiration
                                                                Date</label> <input type="text" id="exp" name="exp"
                                                                                    placeholder="MM/YYYY"></div>
                                                            <div class="form-group col-md-6"><label
                                                                    class="form-control-label">CVV</label> <input type="text"
                                                                                                                  id="cvv"
                                                                                                                  name="cvv"
                                                                                                                  placeholder="***">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 mt-2">
                                                        <div class="row d-flex justify-content-between px-4">
                                                            <p class="mb-1 text-left">Subtotal</p>
                                                            <h6 class="mb-1 text-right">${response.total}</h6>
                                                        </div>
                                                        <div class="row d-flex justify-content-between px-4">
                                                            <p class="mb-1 text-left">Shipping</p>
                                                            <h6 class="mb-1 text-right">$2.99</h6>
                                                        </div>
                                                        <div class="row d-flex justify-content-between px-4" id="tax">
                                                            <p class="mb-1 text-left">Total (tax included)</p>
                                                            <h6 class="mb-1 text-right">${(response.total + 2.99).toFixed(2)}</h6>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="v-pills-profile" role="tabpanel"
                                                 aria-labelledby="v-pills-profile-tab">...
                                            </div>
                                        </div>
                                    </div>`
    checkout.innerHTML = `<span> <span
                        id="checkout">Checkout</span> <span id="check-amt">${(response.total + 2.99).toFixed(2)}</span> </span>`


}

plusButton.onclick = function () {
    console.log("YESYSY")
}

button.forEach(btn => {
    btn.addEventListener("click",loadData)
});


async function loadCart(e) {
    e.preventDefault();
    modal.style.display = "block";
}


plusButton.addEventListener('click', (e) =>{
    console.log("ASDASD");
    console.log(e);
    loadData(e);
})

span.onclick = function() {
    console.log("CLOSE")
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}

cart.addEventListener("click",loadCart);


