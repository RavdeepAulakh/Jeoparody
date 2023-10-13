document.addEventListener('DOMContentLoaded', function() {
    const submitBtn = document.getElementById("deleteForm"); // Note: we changed the input type to "button"
    submitBtn.addEventListener('click', function(event) {
        event.preventDefault();

        let formData = new FormData();
        formData.append("delete_id", document.getElementById("delete_this_id").value);


        fetch("create", {
            method: 'DELETE',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                window.location.href = "submit"; // Redirect to list.html if the submission was successful
            })
            .catch(error => {
                console.error('Error:', error);
                alert("An error occurred. Please try again.");
            });
    });
});



