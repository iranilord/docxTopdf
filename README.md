
# Docx To Pdf 

simpel project convert docx To pdf with Scheduler 
using Spring boot Quartz And Mail server 



## API Reference

#### Add new Scheduler 

```http
  GET /job/add/${name}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Name` | `string` | **Required**. Your Job name |

#### Susspand Job

```http
  GET /job/susspand/{jobName}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `jobName`      | `string` | **Required**. Name of job for susspaning |


#### UnSusspand Job

```http
  GET /job/unsusspand/{jobName}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `jobName`      | `string` | **Required**. Name of job for Unsusspaning |



