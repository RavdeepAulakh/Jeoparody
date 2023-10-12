document.addEventListener('DOMContentLoaded', function() {
    let isCategoriesExpanded = false;
    let isLanguagesExpanded = false;

    const categoriesSelect = document.getElementById('categories');
    const languagesSelect = document.getElementById('languages');

    categoriesSelect.addEventListener('click', function() {
        if (isCategoriesExpanded) {
            categoriesSelect.size = 1;
        } else {
            categoriesSelect.size = 5;
        }
        isCategoriesExpanded = !isCategoriesExpanded;
    });

    languagesSelect.addEventListener('click', function() {
        if (isLanguagesExpanded) {
            languagesSelect.size = 1;
        } else {
            languagesSelect.size = 3;
        }
        isLanguagesExpanded = !isLanguagesExpanded;
    });



    const submitBtn = document.getElementById("submitForm"); // Note: we changed the input type to "button"
    submitBtn.addEventListener('click', function(event) {
        event.preventDefault();

        let formData = new FormData();
        formData.append("image", document.getElementById("img").files[0]);
        formData.append("imageCaption", document.getElementById("imageCaption").value);
        formData.append("categories", document.getElementById("categories").value);
        formData.append("languages", document.getElementById("languages").value);
        formData.append("question", document.getElementById("question").value);
        formData.append("option1", document.getElementById("option1").value);
        formData.append("option1_correct", "true");
        formData.append("option2", document.getElementById("option2").value);
        formData.append("option2_correct", "false");
        formData.append("option3", document.getElementById("option3").value);
        formData.append("option3_correct", "false");
        formData.append("option4", document.getElementById("option4").value);
        formData.append("option4_correct", "false");


        fetch("create", {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                window.location.href = "list"; // Redirect to list.html if the submission was successful
            })
            .catch(error => {
                console.error('Error:', error);
                alert("An error occurred. Please try again.");
            });
    });
});



