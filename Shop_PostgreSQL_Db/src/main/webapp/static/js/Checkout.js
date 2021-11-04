let checkout = document.querySelector('.checkoutBtn');
let userAddres = document.querySelector("#checkout-modal");
let payForm = document.querySelector("#pay-form");
let addresForm = document.querySelector("#address-form");
let invoice = document.querySelector(".modal-content")



checkout.addEventListener('click', function (e) {

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation')

    // Loop over them and prevent submission
    Array.prototype.filter.call(forms, function (form) {
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })
    cartModal.style.display = "block";
}, false)






