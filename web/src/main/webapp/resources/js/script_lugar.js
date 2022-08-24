window.onload = function () {

    let enable = true;
    mapboxgl.accessToken = 'pk.eyJ1IjoibW9ydGlzMTExMSIsImEiOiJja3JyNzExdWwwNzBrMnZwa2JiOHpvZjExIn0.dKhWubS7B_xG4FlBtP0rLg';

    var map = new mapboxgl.Map({
        container: 'mapa',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [-72.309, 4.473],
        zoom: 4.5
    });

    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },
        trackUserLocation: true
    }))

    map.addControl(new mapboxgl.NavigationControl());

    map.on("load", function (e) {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(position => {
                map.flyTo({
                    center: [position.coords.longitude, position.coords.latitude],
                    zoom: 14
                })
            })
        }
    })

    map.on("click", function (e) {

        if (enable) {
            setLtnLng(e.lngLat.lat, e.lngLat.lng)
            enable = false;

            let marker = new mapboxgl.Marker({
                draggable: true
            }).setLngLat([e.lngLat.lng, e.lngLat.lat]).addTo(map);

            marker.on("dragend", function () {
                var lngLat = marker.getLngLat();
                setLtnLng(lngLat.lat, lngLat.lng)
            })
        }

    });
}

function setLtnLng(lat, lng) {
    document.getElementById("crear_lugar:lat_lugar").value = lat
    document.getElementById("crear_lugar:lng_lugar").value = lng

}