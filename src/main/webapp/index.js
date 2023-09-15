
/**
 * Shows first options from the first menu
 */
function showOptions(type) {
    let optionsContainer = document.querySelector('.buttons');
    optionsContainer.innerHTML = ''; // Clearing the main buttons

    if (type === 'single') {
        fetchCategories();
    } else if (type === 'multi') {
        renderOptions([
            {label: "2 Players", onClick: "fetchCategories()"},
            {label: "3 Players", onClick: "fetchCategories()"},
            {label: "4 Players", onClick: "fetchCategories()"}
        ]);
        addBackButton();
    }
}


/**
 * Shows categories for multiplayer
 */
function fetchCategories() {
    let optionsContainer = document.querySelector('.buttons');
    optionsContainer.innerHTML = '';
    fetch("http://localhost:8081/demo_war/categories")
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            const categories = data.map(category => {
                return { label: category, link: `/category/${category.replace(' ', '-').toLowerCase()}` };
            });
            renderOptions(categories);
            addBackButton();
        })
        .catch(error => {
            console.error('Fetch error: ', error.message);
        });
}


/**
 * Add a back button
 */
function addBackButton() {
    let optionsContainer = document.querySelector('.buttons');
    let backButton = document.createElement('div');
    backButton.className = 'button';
    backButton.setAttribute('onclick', 'clearOptions()');
    backButton.innerHTML = '<span>Back</span>';
    optionsContainer.appendChild(backButton);
}

/**
 * Function removes the buttons
 */
function clearOptions() {
    let optionsContainer = document.querySelector('.buttons');
    optionsContainer.innerHTML = `
        <div class="button" onclick="showOptions('single')">
            <span>SinglePlayer</span>
        </div>
        <div class="button" onclick="showOptions('multi')">
            <span>MultiPlayer</span>
        </div>
    `;
}

/**
 * Renders the options
 */
function renderOptions(options) {
    let optionsContainer = document.querySelector('.buttons');
    options.forEach(option => {
        let btn = document.createElement('div');
        btn.className = 'button';
        if (option.link) {
            btn.innerHTML = `<a href="${option.link}">${option.label}</a>`;
        } else if (option.onClick) {
            btn.setAttribute('onclick', option.onClick);
            btn.innerHTML = option.label;
        }
        optionsContainer.appendChild(btn);
    });
}
