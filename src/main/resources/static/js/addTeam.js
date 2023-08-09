const form = document.getElementById('form');
const fullname = document.getElementById('fullname');
const address = document.getElementById('address');
const contact = document.getElementById('contact');
const department = document.getElementById('department');
const email = document.getElementById('email');
const facebook = document.getElementById('facebook');
const github = document.getElementById('github');
const linkedin = document.getElementById('linkedin');
const image = document.getElementById('file');


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

    const fullnamevalue = fullname.value.trim();
    const addressval = address.value.trim();
    const contactval = contact.value.trim();
    const departmentval = department.value.trim();
    const emailValue = email.value.trim();
    const facebookval = facebook.value.trim();
    const githubval = github.value.trim();
    const linkedinval = linkedin.value.trim();
    const fileval = image.value.trim();

    if(fullnamevalue === '') {
        setError(fullname, 'Fullname Field is required');
    } else {
        setSuccess(fullname);
    }
    if(addressval === '') {
        setError(address, 'Address Field is required');
    } else {
        setSuccess(address);
    }

    if(emailValue === '') {
        setError(email, 'Email Field is required');
    } else {
        setSuccess(email);
    }
    if(contactval === '') {
        setError(contact, 'Contact Field is required');
    } else {
        setSuccess(contact);
    }

    if(departmentval === '') {
        setError(department, 'Department Field is required');
    } else {
        setSuccess(department);
    }

    if(facebookval === '') {
        setError(facebook, 'Facebook link Field is required');
    } else {
        setSuccess(facebook);
    }

    if(githubval === '') {
        setError(github, 'Github link Field is required');
    } else {
        setSuccess(github);
    }
    if(linkedinval === '') {
        setError(linkedin, 'Linkedin link Field is required');
    } else {
        setSuccess(linkedin);
    }
    //
    if(fileval === '') {
        setError(image, 'Image is required');
    } else {
        setSuccess(image);
    }


};
