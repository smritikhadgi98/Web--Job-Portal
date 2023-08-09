const toggleForm = () => {
  const container = document.querySelector(".container");
  container.classList.toggle("active");
};



const form = document.getElementById('form');
const email = document.getElementById('email');
const password = document.getElementById('password');

let error = [];

form.addEventListener('submit', e => {
  validateInputs();

  if (error.length > 0) {
    e.preventDefault();
  }

});

const setError = (element, message) => {
  error.push(true);
  const inputControl = element.parentElement;
  const errorDisplay = inputControl.querySelector('.error');

  errorDisplay.innerText = message;
  inputControl.classList.add('error');
  inputControl.classList.remove('success')
}

const setSuccess = element => {
  const inputControl = element.parentElement;
  const errorDisplay = inputControl.querySelector('.error');

  errorDisplay.innerText = '';
  inputControl.classList.add('success');
  inputControl.classList.remove('error');
};

const validateInputs = () => {
  error=[];
  const passwordVal = password.value.trim();
  const emailval = email.value.trim();




  if(emailval === '') {
    setError(email, 'Email Field is required');
  } else {
    setSuccess(email);
  }

  if(passwordVal === '') {
    setError(password, 'Password Field is required');
  } else {
    setSuccess(password);
  }

};
