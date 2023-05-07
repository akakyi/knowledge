# REST
## Определение универское не нашёл, но
Рест это про доступ к ресурсам и операциям над ними. Рест не зависит от протокола, будь то хттп или грпц, но он про то, что операция над данными должна быть понятна и документирована средставми протокола
например для хттп это будет метод запроса (гет, пост и тд), урл до него (по хорошему получение должно выглядеть как мапа) и заголовками
например GET http://myHost/books/12345

## 4 слоя (maturity levels)
https://blog.restcase.com/4-maturity-levels-of-rest-api-design/
* The Swamp of POX
* Resources
* Methods
* Hypermedia Controls

## JAX-RS
### из баелдунга
JAX-RS is nothing more than a specification, a set of interfaces and annotations offered by Java EE. And then, of course, we have the implementations; some of the more well known are RESTEasy and Jersey.
как я помню, оно позволяет вешать аннотации типа @Path("/notifications"), @GET и тд. Кароч @RestController, @RequestMapping из спринга, только для ее.
