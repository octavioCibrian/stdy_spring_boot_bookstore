```toml
name = 'Add Author'
method = 'POST'
url = 'http://localhost:8080/authors'
sortWeight = 1000000
id = 'ac4bdb69-d356-41bc-900b-247ebaa9d110'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '''
{
  "name": "Nombre del Autor",
  "biography": "Biografía del Autor",
  "publisher": {
    "id": 1
  }
}'''
```
