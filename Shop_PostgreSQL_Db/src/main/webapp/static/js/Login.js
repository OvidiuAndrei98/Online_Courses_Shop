let login = document.querySelector('.login');
let loginModal = document.querySelector('.modal-content')
let y = window.matchMedia("(min-width: 700px)")




if (y.matches) { // If media query matches
    registerModal.style.width = '30%'
}

login.addEventListener('click', function (e) {
    loginModal.innerHTML = '';
    loginModal.style.borderRadius = "12px"

    loginModal.innerHTML += `
              <h2 class="text-uppercase text-center mb-5">Login</h2>

              <form class="needs-validation-login" novalidate=""">
     
                <div class="form-outline mb-4">
                  <input type="email" id="loginEmail" class="form-control form-control-lg" name="loginEmail" required/>
                  <label class="form-label" for="loginEmail">Your Email</label>
                  <div class="invalid-feedback"> Valid email is required.</div>
                </div>

                <div class="form-outline mb-4">
                  <input type="password" id="loginPassword" class="form-control form-control-lg" name="loginPassword" required/>
                  <label class="form-label" for="loginPassword">Password</label>
                  <div class="invalid-feedback"> Valid password is required.</div>
                </div>

             
                <div class="d-flex justify-content-center">
                  <button class="btn btn-success btn-block btn-lg gradient-custom-4 text-body loginBtn" id="registerBtn">Login</button>
                </div>

                <p class="text-center text-muted mt-5 mb-0">Don't have an account? <a href="#" class="fw-bold text-body"><u>Register here</u></a></p>

              </form>
`
    registerModalBox.style.display = 'block';

    let forms = document.querySelector('.needs-validation-login');


    document.querySelector('.loginBtn').addEventListener('click', function () {
        let loginData ={"email":document.querySelector('#loginEmail').value, "password":document.querySelector('#loginPassword').value};

        if (forms.loginEmail.value !== "" && forms.loginPassword.value !== "" && forms.loginEmail.value.includes("@")) {
            sendDataLogin(loginData,forms);
        }



        if (forms.checkValidity() === false) {
            document.querySelector('#loginPassword').setCustomValidity("Valid password is required");
            document.querySelector('#loginPassword').classList.add('is-invalid');
            document.querySelector('#loginEmail').setCustomValidity("Valid email is required");
            document.querySelector('#loginEmail').classList.add('is-invalid');
        } else {
            document.querySelector('#loginPassword').classList.remove('is-invalid');
            document.querySelector('#loginEmail').classList.remove('is-invalid');

            document.querySelector('#loginPassword').classList.add('is-valid');
            document.querySelector('#loginEmail').classList.add('is-valid');
        }
    })

})

async function sendDataLogin(data,forms) {
    let API = "/login"


    let request = await fetch(API, {
        headers: {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin" : "*",
            "Access-Control-Allow-Credentials" : true
        },

        method: 'POST',

        body: JSON.stringify(data)

    })

    let response = await request.json()


    if (response.login !== "true") {
        document.querySelector('#loginPassword').setCustomValidity("Password not matching");
        document.querySelector('#loginPassword').classList.add('is-invalid');
    } else if (response.login === "true") {
        document.querySelector('#loginPassword').classList.remove('is-invalid');
        document.querySelector('#loginEmail').classList.remove('is-invalid');

        document.querySelector('#loginPassword').classList.add('is-valid');
        document.querySelector('#loginEmail').classList.add('is-valid');
        location.reload();
    }

}







