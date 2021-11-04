let register = document.querySelector('.register');
let registerModal = document.querySelector('.modal-content')
let x = window.matchMedia("(min-width: 700px)")
let registerButton;
let registerModalBox = document.getElementById("myModal");


if (x.matches) { // If media query matches
    registerModal.style.width = '30%'
}

register.addEventListener('click', function (e) {
    e.preventDefault();
    registerModal.innerHTML = '';
    registerModal.style.borderRadius = "12px"

    registerModal.innerHTML += `
              <h2 class="text-uppercase text-center mb-5">Create an account</h2>

              <form class="needs-validation-register" novalidate="" onsubmit="return false;">
              
                <div class="row">
                      <div class="col-md-6 mb-3">
                        <input type="text" id="registerName" class="form-control form-control-lg" name="registerName" required=""/>
                        <label class="form-label" for="registerName">First Name</label>
                        <div class="invalid-feedback"> Valid first name is required.</div>
                      </div>
                    <div class="col-md-6 mb-3">
                        <input type="text" id="registerName2" class="form-control form-control-lg" name="registerName2" required=""/>
                        <label class="form-label" for="registerName2">Last Name</label>
                        <div class="invalid-feedback"> Valid last name is required.</div>
                    </div>
                </div>

                <div class="form-outline mb-4">
                  <input type="email" id="registerEmail" class="form-control form-control-lg" name="registerEmail" required=""/>
                  <label class="form-label" for="registerEmail">Your Email</label>
                  <div class="invalid-feedback"> Valid email is required.</div>
                </div>

                <div class="form-outline mb-4">
                  <input type="password" id="registerPassword" class="form-control form-control-lg" name="registerPassword" required=""/>
                  <label class="form-label" for="registerPassword">Password</label>
                  <div class="invalid-feedback"> Valid password is required.</div>
                </div>

                <div class="form-outline mb-4">
                  <input type="password" id="registerRepeatPassword" class="form-control form-control-lg" name="registerRepeatPassword" required=""/>
                  <label class="form-label" for="registerRepeatPassword">Repeat your password</label>
                  <div class="invalid-feedback repeatFeedback"> Passwords do not Match.</div>
                </div>

                <div class="form-check d-flex flex-column justify-content-center mb-5">
                  <input
                    class="form-check-input me-2"
                    type="checkbox"
                    value=""
                    id="termsCheck"
                    required
                  />
                  <label class="form-check-label" for="termsCheck">
                    I agree all statements in <a href="#" class="text-body"><u>Terms of service</u></a>
                  </label>
                </div>

                <div class="d-flex justify-content-center">
                  <button class="btn btn-success btn-block btn-lg gradient-custom-4 text-body" id="registerBtn">Register</button>
                </div>

                <p class="text-center text-muted mt-5 mb-0">Have already an account? <a href="#" class="fw-bold text-body"><u>Login here</u></a></p>

              </form>
`
    registerModalBox.style.display = 'block';
    registerButton = document.querySelector('#registerBtn');
    registerButton.addEventListener('click', function (e) {


        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        let forms = document.querySelector('.needs-validation-register')

        let name = document.querySelector('#registerName');
        let email = document.querySelector('#registerEmail');



        // Loop over them and prevent submission

            forms.addEventListener('submit', function (event) {
                let password1 = document.querySelector('#registerPassword').value;
                let password2 = document.querySelector('#registerRepeatPassword').value;

                if (forms.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                checkPsw(event, password1, password2);

                })
                    forms.classList.add('was-validated')


                function checkPsw(e, password1, password2) {
                    if (password1 !== password2) {
                        document.querySelector('#registerRepeatPassword').setCustomValidity("Password not matching");
                        document.querySelector('#registerRepeatPassword').classList.add('is-invalid');
                        console.log("NUNU")
                        document.querySelector('.repeatFeedback').style.display = "block";
                        e.preventDefault();
                        e.stopPropagation();
                    } else if (password1 === "" && password2 === "") {
                        document.querySelector('#registerRepeatPassword').setCustomValidity("Password cannot be empty");
                        document.querySelector('#registerRepeatPassword').classList.add('is-invalid');
                    }
                    else {
                        document.querySelector('#registerRepeatPassword').setCustomValidity("");
                        let registerData ={"firstName":document.querySelector('#registerName').value, "lastName":document.querySelector('#registerName2').value, "email":document.querySelector('#registerEmail').value, "password":document.querySelector('#registerPassword').value};
                        sendData(registerData)
                    }


                }

            }, false)

        registerModalBox.style.display = "block";
})

window.onclick = function(event) {
    if (event.target === registerModalBox) {
        registerModalBox.style.display = "none";
    }

}

async function sendData(data) {
    let API = "/register"


    let request = await fetch(API, {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },

        method: 'POST',

        body: JSON.stringify(data)

    })

}




