let MP = document.getElementById('multiPlayer');
let mode;

document.addEventListener("DOMContentLoaded", function() {
    MP.addEventListener('click', function () {
        mode = MP.id;
        console.log(mode);

        fetch('/demo_war_exploded/gameMode', {
            method: 'POST',
            headers: {
                "Content-Type": 'text/plain',
            },
            body: mode,
        })
            .then((response) => {
                if (response.ok) {
                    console.log('sent game mode');
                } else {
                    console.log('error - game mode not sent');
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    });
})


let SP = document.getElementById('singlePlayer')
SP.addEventListener('click', function(){
    mode = SP.id;
    console.log(mode)

    fetch('/demo_war_exploded/gameMode', {
        method: 'POST',
        headers: {
            "Content-Type": 'text/plain',
        },
        body: mode,
    })
        .then((response) => {
            if (response.ok) {
                console.log('sent game mode');
            } else {
                console.log('error - game mode not sent');
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });


})
