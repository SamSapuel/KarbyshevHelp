const homeButton = document.getElementById("homeButton")

window.onload = function () {
    // homeButton.addEventListener("click", () => {
    //
    // })
    fetch("http://localhost:25580/api/v1/users")
        .then(res => {
            console.log(res)
        })
}

