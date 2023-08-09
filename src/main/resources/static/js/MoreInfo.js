const form = document.getElementById('form');
const Fullname = document.getElementById('Fullname');
const Email = document.getElementById('Email');
const Mobile = document.getElementById('Mobile');
const Address = document.getElementById('Address');
const Gender = document.getElementById('Gender');
const Jobs = document.getElementById('Jobs');
const Jobsaddress = document.getElementById('Jobsaddress');
const mobileNo = document.getElementById('mobileNo');
const Id = document.getElementById('Id');
const idNumber = document.getElementById('idNumber');
const file = document.getElementById('file');


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
    // const Mobileval = Mobile.value.trim();
    const Addressval = Address.value.trim();
    const Genderval = Gender.value.trim();
    const Jobsval = Jobs.value.trim();
    const Jobsaddressval = Jobsaddress.value.trim();
    const mobileNoval = mobileNo.value.trim();
    const Idval = Id.value.trim();
    const idNumberval = idNumber.value.trim();
    const fileval = file.value.trim();



    if(Addressval === '') {
        setError(Address, 'Address Field is required');
    } else {
        setSuccess(Address);
    }


    if(Genderval === 'select') {
        setError(Gender, 'Select the gender');
    } else {
        setSuccess(Gender);
    }

    if(Idval === 'select') {
        setError(Id, 'Select the Type of ID');
    } else {
        setSuccess(Id);
    }


    if(idNumberval === '') {
        setError(idNumber, 'Id Number is required');
    } else {
        setSuccess(idNumber);
    }


    var allowedExtensions = /(\.pdf)$/i;

    if(fileval === '') {
        setError(file, 'CV is required');
    }
    else  if (!allowedExtensions.exec(fileval)) {
        setError(file,"Please upload a PDF file.");
        // image.value = "";
    }  else {
        setSuccess(file);
    }




};

function validateForm() {
    var fileInput = document.getElementById("file");
    var filePath = fileInput.value;
    var allowedExtensions = /(\.pdf)$/i;

    if (!allowedExtensions.exec(filePath)) {
        alert("Please upload a PDF file.");
        fileInput.value = "";
        return false;
    } else {
        return true;
    }
}