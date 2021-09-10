function addRoverUnits() {
    const number = document.getElementById("unit").value;

    for(let i = 0; i < number; i++) {

        let input = document.createElement("input");
        input.type = "number";
        container.appendChild(input);
        container.appendChild(document.createElement("br"));
    }
}
