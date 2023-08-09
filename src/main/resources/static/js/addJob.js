const form = document.getElementById('form');
const jobTitle = document.getElementById('jobTitle');
const category = document.getElementById('category');
const type = document.getElementById('type');
const address = document.getElementById('address');
const contact = document.getElementById('contact');
const salary = document.getElementById('salary');
const skill1 = document.getElementById('skill1');
const skill2 = document.getElementById('skill2');
const skill3 = document.getElementById('skill3');
const skill4 = document.getElementById('skill4');
const time = document.getElementById('time');
const descri = document.getElementById('descri');
const education = document.getElementById('education');
const qualification = document.getElementById('qualification');
const qualification2 = document.getElementById('qualification2');
const qualification3 = document.getElementById('qualification3');
const experience = document.getElementById('experience');
const experience2 = document.getElementById('experience2');
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
    const jobTitleval = jobTitle.value.trim();
    const categoryval = category.value.trim();
    const typeval = type.value.trim();
    const addressval = address.value.trim();
    const contactval = contact.value.trim();
    const salaryval = salary.value.trim();
    const skill1val = skill1.value.trim();
    const skill2val = skill2.value.trim();
    const skill3val = skill3.value.trim();
    const skill4val = skill4.value.trim();
    const timeval = time.value.trim();
    const descrival = descri.value.trim();
    const educationval = education.value.trim();
    const qualificationval = qualification.value.trim();
    const qualification2val = qualification2.value.trim();
    const qualification3val = qualification3.value.trim();
    const experianceval = experience.value.trim();
    const experiance2val = experience2.value.trim();
    const fileval = image.value.trim();

    if(jobTitleval === '') {
        setError(jobTitle, 'Fullname Field is required');
    } else {
        setSuccess(jobTitle);
    }
    if(categoryval === '') {
        setError(category, 'Category Field is required');
    } else {
        setSuccess(category);
    }

    if(typeval === '') {
        setError(type, 'Email Field is required');
    } else {
        setSuccess(type);
    }
    if(addressval === '') {
        setError(address, 'Address Field is required');
    } else {
        setSuccess(address);
    }

    if(contactval === '') {
        setError(contact, 'Contact Field is required');
    } else {
        setSuccess(contact);
    }

    if(salaryval === '') {
        setError(salary, 'Salary Field is required');
    } else {
        setSuccess(salary);
    }
    if(skill1val === '') {
        setError(skill1, 'Skill1 Field is required');
    } else {
        setSuccess(skill1);
    }
    if(skill2val === '') {
        setError(skill2, 'Skill2 Field is required');
    } else {
        setSuccess(skill2);
    }

    if(skill3val === '') {
        setError(skill3, 'Skill3 Field is required');
    } else {
        setSuccess(skill3);
    }
    if(skill4val === '') {
        setError(skill4, 'Skill4 Field is required');
    } else {
        setSuccess(skill4);
    }

    if(timeval === '') {
        setError(time, 'Time description Field is required');
    } else {
        setSuccess(time);
    }
    if(descrival === '') {
        setError(descri, 'Description Field is required');
    } else {
        setSuccess(descri);
    }

    if(educationval === '') {
        setError(education, 'Education Field is required');
    } else {
        setSuccess(education);
    }

    if(qualificationval === '') {
        setError(qualification, 'Qualification Field is required');
    } else {
        setSuccess(qualification);
    }
    if(qualification2val === '') {
        setError(qualification2, 'Qualification2 Field is required');
    } else {
        setSuccess(qualification2);
    }

    if(qualification3val === '') {
        setError(qualification3, 'Qualification3 Field is required');
    } else {
        setSuccess(qualification3);
    }
    if(experianceval === '') {
        setError(experience, 'Experience Field is required');
    } else {
        setSuccess(experience);
    }

    if(experiance2val === '') {
        setError(experience2, 'Experience2 Field is required');
    } else {
        setSuccess(experience2);
    }

    if(fileval === '') {
        setError(image, 'Image is required');
    } else {
        setSuccess(image);
    }


};
