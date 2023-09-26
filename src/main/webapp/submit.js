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
});