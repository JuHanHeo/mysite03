<!--///////////////////////// 지도 /////////////////////////-->
            <p class="lead">
                <i class="glyphicon glyphicon-map-marker"></i>
                장소
                <span class="location"> • 창원대학교 55호관 506호</span>
                <input id="address" type="hidden" value="경남 창원 창원대학교 55호관">
            </p>

            <div id="map"></div>
                <script>
                    function initMap() {

                        var geocoder = new google.maps.Geocoder();
                        var address = document.getElementById('address').value;
                        setCenter(geocoder, address);
                    }

                    function setCenter(geocoder, address) {
                        geocoder.geocode({
                            'address': address
                        }, function(results, status) {
                            if (status === google.maps.GeocoderStatus.OK) {
                                var map = new google.maps.Map(document.getElementById('map'), {
                                    zoom: 14,
                                    center: results[0].geometry.location
                                });
                                var marker = new google.maps.Marker({
                                    position: results[0].geometry.location,
                                    map: map
                                });
                            } else {
                                alert('Geocode was not successful for the following reason: ' + status);
                                // return null;
                            }
                        });
                    }
                </script>
                <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCEl_BNACj7LCF2Hzs2Ft-9-XBf5Z4AZLQ&callback=initMap">
                </script>