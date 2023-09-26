// webapp/WEB-INF/language.js
function selectLanguage(languageId) {
    // Redirect to the CategoryServlet with the selected language ID as a parameter
    window.location.href = 'categories?languageId=' + languageId;
}
