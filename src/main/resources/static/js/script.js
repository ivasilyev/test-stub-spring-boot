document.addEventListener("DOMContentLoaded", function() {
    // Get the elements
    const endpointName = document.getElementById("meta_endpoint_name").innerText;
    const integerValue = document.getElementById("delay_ms");
    const inputValue = document.getElementById("new_delay");
    const setButton = document.getElementById("set_button");
    const resetButton = document.getElementById("reset_button");

    //document.getElementById("main_header").innerHTML = `Endpoint ${endpointName}`;

    // Fetch the current integer value from the backend
    fetch(`/${endpointName}/api/get-delay`)
        .then(response => response.json())
        .then(data => {
            integerValue.textContent = data["delayMs"];
        });

    // Set button click event handler
    setButton.addEventListener("click", function() {
        const newDelay = parseInt(inputValue.value);
        if (!isNaN(newDelay)) {
            // Send the new value to the backend
            fetch(`/${endpointName}/api/set-delay`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ delayMs: newDelay })
            })
                .then(response => response.json())
                .then(data => {
                    integerValue.textContent = data["delayMs"];
                });
        }
    });

    // Reset button click event handler
    resetButton.addEventListener("click", function() {
        // Reset the value to default in the backend
        fetch(`/${endpointName}/api/reset-delay`, { method: "POST" })
            .then(response => response.json())
            .then(data => {
                let newDelay = data["delayMs"];
                inputValue.value = null;
                integerValue.textContent = newDelay;
            });
    });
});

