# Product Search Module (Spring Data JPA)

## Features Implemented
- Product entity with fields: `id`, `name`, `category`, `price`
- Derived query methods:
  - `findByCategory(String category)`
  - `findByPriceBetween(double min, double max)`
- JPQL query methods:
  - Sort by price
  - Fetch products above a price
  - Fetch products by category
- REST endpoints:
  - `GET /products/category/{category}`
  - `GET /products/filter?min=&max=`
  - `GET /products/sorted`
  - `GET /products/expensive/{price}`

## Run
```bash
mvn spring-boot:run
```

## Sample Data
Preloaded from `src/main/resources/data.sql` with categories such as Electronics, Fashion, Furniture, and Home Appliances.

## Postman Test URLs
Use `http://localhost:8080` as base URL.

1. Category search
   - `GET /products/category/Electronics`
   - (JPQL category test) `GET /products/category/Fashion?mode=jpql`

2. Price range filter
   - `GET /products/filter?min=80&max=300`

3. Sorted by price
   - `GET /products/sorted`

4. Expensive products (above price)
   - `GET /products/expensive/200`

## Verified Outputs (example)
- `Electronics` category returns 2 products.
- Filter `80-300` returns 4 products.
- Sorted endpoint returns products in ascending price order.
- Expensive `>200` returns 3 products.
