# Play e-commerce CRUD

A barebones e-commerce CRUD web application based on a simple `REST API`.

## Features
- A `ProductController` implementation
- Working CRUD endpoints for `Products`, `Categories` and `Cart` (index available below)

Used technologies:
- `Scala` (version 2)
- the `Play` web framework
- `insomnia` as a open-source `Postman` alternative 

## Usage
The web application can be run with 
```
sbt run
``` 
and tested with the supplied `insomniaCollection.json` file.

## Available endpoints 
Available endpoints can be imported to `insomnia` from the `insomniaCollection.json` file:<br/>
(These are just endpoint names, not the actual URL/URI paths)<br/>
![image](https://user-images.githubusercontent.com/75375838/230937947-52df615c-8282-43b9-9196-300b4b19fd30.png)

## Example output
- The `showCategory` endpoint:<br/>
![image](https://user-images.githubusercontent.com/75375838/230939147-6a0032e3-aad1-48b3-bef1-e856d2292ad3.png)
- Using the `addCartMember` enpoint the first and then the second time on the same prouct:<br/>
![image](https://user-images.githubusercontent.com/75375838/230953460-89d99bff-e379-4bf1-a6b1-5eb3ad7c2416.png)
![image](https://user-images.githubusercontent.com/75375838/230939359-46db3f61-c7ed-4e5f-9b45-73fff9f63f14.png)

Note: There are many supported usage scenarios, f.e. buying a certain quantity shortens the stock quantity of that product etc.

etc. Those all can be checked with `insomnia`.
