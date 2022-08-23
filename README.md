# SmyBteApi
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/SmylerMC/smybteapi/Java%20CI%20with%20Gradle?style=flat-square)


Web API with useful coordinate convertion for BTE.

For an online version of the converter, use this website: https://terramap.thesmyler.fr/coordinateconverter.html.


For an API request, the GET requests must have this following pattern:

MC to Geographic > `https://smybteapi.thesmyler.fr/projection/toGeo?mcpos=2847508.133,-5051310.918&mcpos=2850756.798,-5050445.770`\
Geographic to MC > `https://smybteapi.thesmyler.fr/projection/fromGeo?geopos=48.858,2.294&geopos=48.860,2.337`

Up to 300 coordinates can be converted at once, each coordinate pair separated by the `&` symbol
