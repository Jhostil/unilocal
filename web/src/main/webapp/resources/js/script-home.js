function crearMapa(lugares) {
    mapboxgl.accessToken = 'pk.eyJ1IjoibW9ydGlzMTExMSIsImEiOiJja3JyNzExdWwwNzBrMnZwa2JiOHpvZjExIn0.dKhWubS7B_xG4FlBtP0rLg';
    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [-72.309, 4.473],
        zoom: 4.5
    });

    map.on("load", function (e) {
        ubicarLugares(lugares, map);

    });
}

function ubicarLugares(lugares, map) {
    let bounds = new mapboxgl.LngLatBounds();

    console.log(lugares.valueOf())
    for (let l of lugares) {
        new mapboxgl.Marker().setLngLat([l.lng, l.lat]).setPopup(new mapboxgl.Popup().setHTML("<strong>" + l.nombre + "</strong><br>" + l.descripcion + "<br> <a href='http://localhost:8080/detalleLugar.xhtml?lugar=" + l.id + "'>Ir a detalle</a>")).addTo(map).togglePopup();
        bounds.extend([l.lng, l.lat]);
    }
    map.fitBounds(bounds, {padding: 100});
    document.getElementById("map").style.visibility = "visible";
}