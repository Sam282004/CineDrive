const fila1 = document.querySelector('#carousel-1');
const peliculas1 = document.querySelectorAll('#carousel-1 .pelicula');
const flechaIzquierda1 = document.getElementById('flecha-izquierda-1');
const flechaDerecha1 = document.getElementById('flecha-derecha-1');
const indicadores1 = document.querySelector('#carousel-1 .indicadores');

// Añade tus event listeners y funciones de carrusel para el primer carrusel
flechaDerecha1.addEventListener('click', () => {
    fila1.scrollLeft += fila1.offsetWidth;

    const indicadorActivo = document.querySelector('#carousel-1 .indicadores .activo');
    if (indicadorActivo.nextSibling) {
        indicadorActivo.nextSibling.classList.add('activo');
        indicadorActivo.classList.remove('activo');
    }
});

flechaIzquierda1.addEventListener('click', () => {
    fila1.scrollLeft -= fila1.offsetWidth;

    const indicadorActivo = document.querySelector('#carousel-1 .indicadores .activo');
    if (indicadorActivo.previousSibling) {
        indicadorActivo.previousSibling.classList.add('activo');
        indicadorActivo.classList.remove('activo');
    }
});
const numeroPaginas1 = Math.ceil(peliculas1.length / 5);
for (let i = 0; i < numeroPaginas1; i++) {
    const indicador = document.createElement('button');

    if (i === 0) {
        indicador.classList.add('activo');
    }

    document.querySelector('#carousel-1 .indicadores').appendChild(indicador);

    indicador.addEventListener('click', (e) => {
        fila1.scrollLeft = i * fila1.offsetWidth;

        document.querySelector('#carousel-1 .indicadores .activo').classList.remove('activo');
        e.target.classList.add('activo');
    });
}

// Hover para el primer carrusel


peliculas1.forEach((pelicula) => {
	pelicula.addEventListener('mouseenter', (e) => {
		const elemento = e.currentTarget;
		setTimeout(() => {
			peliculas1.forEach(pelicula => pelicula.classList.remove('hover'));
			elemento.classList.add('hover');
		}, 300);
	});
});

fila1.addEventListener('mouseleave', () => {
	peliculas1.forEach(pelicula => pelicula.classList.remove('hover'));
});