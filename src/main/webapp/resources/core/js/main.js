document.getElementById("agregarRespuesta").addEventListener("click", function() {
    var respuestaIndex = document.querySelectorAll('[name^="respuestas"]').length;

    var newResponseDiv = document.createElement("div");
    newResponseDiv.innerHTML = `
        <label>Ingrese una respuesta:</label>
        <input type="text" name="respuestas[${respuestaIndex}].descripcion" required />
        <input type="radio" name="respuestas[${respuestaIndex}].esCorrecta" value="true" /> Correcta
    `;

    // Agrega una clase a la respuesta 
    newResponseDiv.classList.add("respuesta-container");

    var form = document.querySelector("form");

    // Inserta el nuevo campo de respuesta antes del botón de agregar
    form.insertBefore(newResponseDiv, document.getElementById("agregarRespuesta"));
});
