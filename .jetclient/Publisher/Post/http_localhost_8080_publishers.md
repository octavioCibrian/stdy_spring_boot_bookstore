```toml
name = 'http://localhost:8080/publishers'
method = 'POST'
url = 'http://localhost:8080/author'
sortWeight = 1000000
id = '3be81b7d-c0fa-4e2c-ba20-8e370c78ae05'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '{"name": "Publisher Name", "address": "Address of the publisher"}'
```
