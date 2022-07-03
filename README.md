# Marketplace API
This is an API for <a href="https://github.com/beleavemebe/marketplace-app">the marketplace app</a>.

Below is a lame description for it, since I have no wish to generate a proper documentation. See the source code for the details 💩
## Endpoints
#### GET /actions/refresh-dataset
Trigger re-generation of the whole dataset of the API.
#### GET /status
Get the status of the API. 
#### GET /departments
Get all departments.
#### GET /pic/{department}/{image}
Get a picture for the department.
#### GET /products
Get up to _limit_ products on the _page_-th page.
#### GET /products/toprated
Get up to _limit_ highest rated products on the _page_-th page.
#### GET /products/topsales
Get up to _limit_ best selling products on the _page_-th page.
#### GET /products/random
Get up to _limit_ random products on the _page_-th page.
#### GET /products/people-are-buying
Get the recently ordered products.
#### GET /products/{id}
Get details for product _id_.
#### GET /products/similar/{id}
Get similar products for product _id_.
#### GET /products/filters/{query}
Get available filters for the products matching the query _query_
#### POST /products/search
Get up to _limit_ products matching the term _term_ on the _page_-th page.

Body - a `FiltersConfiguration` instance or null.
#### POST /actions/checkout
Checkout with an order.

Body - an `OrderRequest` instance.
