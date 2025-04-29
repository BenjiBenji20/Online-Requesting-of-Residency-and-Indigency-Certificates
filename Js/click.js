function toggleForm(formId) {
    let forms = document.querySelectorAll('.registration-form');

    forms.forEach(form => {
        if (form.id === formId) {
            if (form.classList.contains("active")) {
                form.classList.remove("active");
            } else {
                forms.forEach(f => f.classList.remove("active")); // Hide other forms
                form.classList.add("active");
            }
        } else {
            form.classList.remove("active");
        }
    });
}

