# Log dos requisitos propostos

## R01

> STATUS: COMPLETO

### Como testar

#### POST:

> */api/v1/insert-articles-request/*

#### BODY

```JSON
{
  // Mudamos o nome de "articles" para "products"
  "products": [
    {
      "productId": 1,
      "name": "Serra de Bancada",
      "category": "Ferramentas",
      "brand": "FORTGPRO",
      "price": 180000,
      "quantity": 5,
      "freeShipping": true,
      "prestige": "****"
    },
    {
      "productId": 2,
      "name": "Furadeira",
      "category": "Ferramentas",
      "brand": "Black & Decker",
      "price": 50000,
      "quantity": 7,
      "freeShipping": true,
      "prestige": "****"
    }
  ]
}
```

#### RESPONSE (Primeiro request)

```JSON
{
  "products": [
    {
      "id": 13,
      "name": "Serra de Bancada",
      "quantity": 5
    },
    {
      "id": 14,
      "name": "Furadeira",
      "quantity": 7
    }
  ]
}
```

## R02

> STATUS: COMPLETO

### Como testar

#### GET:

> */api/v1/articles/*

#### BODY

#### RESPONSE (Primeiro request)

```JSON
{
  "products": [
    {
      "id": 13,
      "name": "Serra de Bancada",
      "quantity": 5
    },
    {
      "id": 14,
      "name": "Furadeira",
      "quantity": 7
    }
  ]
```

## R03

> STATUS: COMPLETO

### Como testar

#### GET:

> */api/v1/articles?category=ferramentas*

#### BODY

#### RESPONSE (Primeiro request)

```JSON
[
  {
    "id": 1,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 2,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 3,
    "name": "Soldadora",
    "quantity": 10
  },
  {
    "id": 1,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 2,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 3,
    "name": "Soldadora",
    "quantity": 10
  },
  {
    "id": 13,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 14,
    "name": "Furadeira",
    "quantity": 7
  }
]
```

## R04

> STATUS: COMPLETO

#### GET:

> */api/v1/articles?category=Ferramentas&brand=Black%20%26%20Decker*

#### BODY

#### RESPONSE (Primeiro request)

```JSON
[
  {
    "id": 2,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 3,
    "name": "Soldadora",
    "quantity": 10
  },
  {
    "id": 2,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 3,
    "name": "Soldadora",
    "quantity": 10
  },
  {
    "id": 14,
    "name": "Furadeira",
    "quantity": 7
  }
]
```

## R05

> STATUS: COMPLETO

#### GET:

> */api/v1/articles?order=0&category=ferramentas*

#### BODY

#### RESPONSE (Primeiro request)

```JSON
[
  {
    "id": 2,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 2,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 14,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 1,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 1,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 13,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 3,
    "name": "Soldadora",
    "quantity": 10
  },
  {
    "id": 3,
    "name": "Soldadora",
    "quantity": 10
  }
]
```

## R06

> STATUS: COMPLETO

#### GET:

> */api/v1/articles?order=2*

#### BODY

#### RESPONSE (Primeiro request)

```JSON
[
  {
    "id": 7,
    "name": "Redmi Note 9",
    "quantity": 15
  },
  {
    "id": 7,
    "name": "Redmi Note 9",
    "quantity": 15
  },
  {
    "id": 1,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 1,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 13,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 8,
    "name": "Smartwatch",
    "quantity": 20
  },
  {
    "id": 8,
    "name": "Smartwatch",
    "quantity": 20
  },
  {
    "id": 2,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 2,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 14,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 3,
    "name": "Soldadora",
    "quantity": 10
  },
  {
    "id": 3,
    "name": "Soldadora",
    "quantity": 10
  },
  {
    "id": 12,
    "name": "Shorts",
    "quantity": 9
  },
  {
    "id": 12,
    "name": "Shorts",
    "quantity": 9
  },
  {
    "id": 4,
    "name": "Chuteira",
    "quantity": 6
  },
  {
    "id": 4,
    "name": "Chuteira",
    "quantity": 6
  },
  {
    "id": 5,
    "name": "Mini Cama elastica",
    "quantity": 4
  },
  {
    "id": 5,
    "name": "Mini Cama elastica",
    "quantity": 4
  },
  {
    "id": 6,
    "name": "Camiseta",
    "quantity": 2
  },
  {
    "id": 6,
    "name": "Camiseta",
    "quantity": 2
  },
  {
    "id": 9,
    "name": "Camisa",
    "quantity": 2
  },
  {
    "id": 9,
    "name": "Camisa",
    "quantity": 2
  },
  {
    "id": 10,
    "name": "Calças",
    "quantity": 6
  },
  {
    "id": 10,
    "name": "Calças",
    "quantity": 6
  },
  {
    "id": 11,
    "name": "Meias",
    "quantity": 8
  },
  {
    "id": 11,
    "name": "Meias",
    "quantity": 8
  }
]
```

## R07

> STATUS: COMPLETO

#### GET:

> */api/v1/articles?order=3*

#### BODY

#### RESPONSE (Primeiro request)

```JSON
[
  {
    "id": 11,
    "name": "Meias",
    "quantity": 8
  },
  {
    "id": 11,
    "name": "Meias",
    "quantity": 8
  },
  {
    "id": 10,
    "name": "Calças",
    "quantity": 6
  },
  {
    "id": 10,
    "name": "Calças",
    "quantity": 6
  },
  {
    "id": 9,
    "name": "Camisa",
    "quantity": 2
  },
  {
    "id": 9,
    "name": "Camisa",
    "quantity": 2
  },
  {
    "id": 6,
    "name": "Camiseta",
    "quantity": 2
  },
  {
    "id": 6,
    "name": "Camiseta",
    "quantity": 2
  },
  {
    "id": 5,
    "name": "Mini Cama elastica",
    "quantity": 4
  },
  {
    "id": 5,
    "name": "Mini Cama elastica",
    "quantity": 4
  },
  {
    "id": 4,
    "name": "Chuteira",
    "quantity": 6
  },
  {
    "id": 4,
    "name": "Chuteira",
    "quantity": 6
  },
  {
    "id": 12,
    "name": "Shorts",
    "quantity": 9
  },
  {
    "id": 12,
    "name": "Shorts",
    "quantity": 9
  },
  {
    "id": 3,
    "name": "Soldadora",
    "quantity": 10
  },
  {
    "id": 3,
    "name": "Soldadora",
    "quantity": 10
  },
  {
    "id": 2,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 2,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 14,
    "name": "Furadeira",
    "quantity": 7
  },
  {
    "id": 8,
    "name": "Smartwatch",
    "quantity": 20
  },
  {
    "id": 8,
    "name": "Smartwatch",
    "quantity": 20
  },
  {
    "id": 1,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 1,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 13,
    "name": "Serra de Bancada",
    "quantity": 5
  },
  {
    "id": 7,
    "name": "Redmi Note 9",
    "quantity": 15
  },
  {
    "id": 7,
    "name": "Redmi Note 9",
    "quantity": 15
  }
]
```

## R08

> STATUS: COMPLETO

#### POST:

> */api/v1/purchase-request*

#### BODY

```JSON
{
  "purchases": [
    {
      "productId": 1,
      "name": "Serra de Bancada",
      "brand": "FORTGPRO",
      "quantity": 5
    },
    {
      "productId": 2,
      "name": "Furadeira",
      "brand": "Black & Decker",
      "quantity": 7
    }
  ],
  "customerId": 1
}
```

#### RESPONSE (Primeiro request)

```JSON
[
  {
    "purchases": [
      {
        "name": "Serra de Bancada",
        "category": "Ferramentas",
        "brand": "FORTGPRO",
        "price": 1800,
        "quantity": 5,
        "freeShipping": true,
        "prestige": "****",
        "productId": 1
      },
      {
        "name": "Furadeira",
        "category": "Ferramentas",
        "brand": "Black & Decker",
        "price": 500,
        "quantity": 7,
        "freeShipping": true,
        "prestige": "****",
        "productId": 2
      }
    ],
    "purchaseId": 4,
    "customerId": 1,
    "totalPrice": 2300
  }
]
```

## R09

> STATUS: PARCIALMENTE COMPLETO

#### GET:

> */api/v1/purchase-request*

#### BODY

#### RESPONSE (Primeiro request)

## R10

> STATUS: COMPLETO

#### GET:

> */api/v1/purchase-request/cart/1*

**No caso você pode substituir o */1* pelo ID do customer relacionado a compra**

#### BODY

#### RESPONSE (Primeiro request)

```JSON
{
  "products": [
    {
      "name": "Serra de Bancada",
      "category": "Ferramentas",
      "brand": "FORTGPRO",
      "price": 1800,
      "quantity": 5,
      "freeShipping": true,
      "prestige": "****",
      "productId": 1
    },
    {
      "name": "Furadeira",
      "category": "Ferramentas",
      "brand": "Black & Decker",
      "price": 500,
      "quantity": 7,
      "freeShipping": true,
      "prestige": "****",
      "productId": 2
    }
  ],
  "total": 12500
}
```

## R11

> STATUS: COMPLETO

#### POST: > */api/v1/customers/*

#### BODY

```JSON
[
{
"name": "test",
"email": "newclient2@tosave2.com",
"address": {
"street": "street 1",
"neighborhood": "Neigh 1",
"uf": "SC",
"country": "Brazil",
"zipcode": "00000000"
}
}
]
```

#### RESPONSE (Primeiro request)

```JSON
[
  {
    "id": "1",
    "name": "test",
    "email": "newclient2@tosave2.com",
    "uri": "http://localhost:8080/api/v1/customers/1"
  }
]
```

## R12

> STATUS: COMPLETO

#### GET:

> */api/v1/customers/*

#### BODY

#### RESPONSE (Primeiro request)

```JSON
[
  {
    "name": "test",
    "email": "newclient2@tosave2.com",
    "address": {
      "street": "street 1",
      "neighborhood": "Neigh 1",
      "uf": "SC",
      "country": "Brazil",
      "zipcode": "00000000"
    }
  }
]
```

## R13

> STATUS: COMPLETO

#### GET:

> */api/v1/customers?uf=SC*

#### BODY

#### RESPONSE (Primeiro request)

```JSON
[
  {
    "name": "test",
    "email": "newclient2@tosave2.com",
    "address": {
      "street": "street 1",
      "neighborhood": "Neigh 1",
      "uf": "SC",
      "country": "Brazil",
      "zipcode": "00000000"
    }
  }
]
```