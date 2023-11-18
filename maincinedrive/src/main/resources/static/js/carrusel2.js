const fila2 = document.querySelector(".contenedor-carousel2");
    const peliculas2 = document.querySelectorAll(".pelicula2");

    const flechaIzquierda2 = document.getElementById("flecha-izquierda2");
    const flechaDerecha2 = document.getElementById("flecha-derecha2");

    flechaDerecha2.addEventListener("click", () => {
        fila2.scrollLeft += fila2.offsetWidth;
        const indicadorActivo2 = document.querySelector(".indicadores2 .activo");
        if (indicadorActivo2.nextSibling) {
            indicadorActivo2.nextSibling.classList.add("activo");
            indicadorActivo2.classList.remove("activo");
        }
    });
    flechaIzquierda2.addEventListener("click", () => {
        fila2.scrollLeft -= fila2.offsetWidth;
        const indicadorActivo2 = document.querySelector(".indicadores2 .activo");
        if (indicadorActivo2.previousSibling) 
        {
            indicadorActivo2.previousSibling.classList.add("activo");
            indicadorActivo2.classList.remove("activo");
        }
    });

    const numeroPaginas2 = Math.ceil(peliculas2.length / 5);
    for (let i = 0; i < numeroPaginas2; i++) {
        const indicador2 = document.createElement("button");
        if (i === 0) {
            indicador2.classList.add("activo");
        }
        document.querySelector(".indicadores2").appendChild(indicador2);
        indicador2.addEventListener("click", (e) => {
            fila2.scrollLeft = i * fila2.offsetWidth;
            document
                .querySelector(".indicadores2 .activo")
                .classList.remove("activo");
            e.target.classList.add("activo");
        });
    }

    peliculas2.forEach((pelicula2) => {
        pelicula2.addEventListener("mouseenter", (e) => {
            const elemento = e.currentTarget;
            setTimeout(() => {
                peliculas2.forEach((pelicula2) => pelicula2.classList.remove("hover"));
                elemento.classList.add("hover");
            }, 100);
        });
    });
    fila2.addEventListener("mouseleave", () => {
        peliculas2.forEach((pelicula2) => pelicula2.classList.remove("hover"));
    });