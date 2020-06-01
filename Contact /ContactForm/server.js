// 1- create a folder for project
// 2-npm init (use your own or use default)
//3- npm install --save-dev eslint eslint-plugin-import eslint-plugin-node eslint-plugin-promise eslint-plugin-standard eslint-config-standard
//4- npm install express
'use strict'
const DB = require('./dao')
//force variable declaration before they can  be used
// npm install pg
const express = require('express')
const app = express()

app.use(express.static('public_html'))

//EJS template engine setup
//templates must be in views folder
app.set('view engine', 'ejs')

//use CORS(npm install cors)
//make the API available to everyone in teh universe
var cors = require('cors')
app.use(cors())

//Home page
/*app.get("/", function(request, response) {
  response.sendFile(__dirname + "/public_html/index.html");
});*/

app.use(express.json()) //used to parse json boides
app.use(express.urlencoded()) //parse url-encoded bodies

app.listen(8000, function () {
  console.log('server listening to port 8000')
})

//RESt API for ajax calls------------
//get the list of all contacts
app.get('/contacts/', function (request, response) {
  // response.sendFile(__dirname+'/index.html')
  DB.connect()
  DB.query('select * from contact order by id asc', [], result => {
    console.log('Number of rows in table: ' + result.rowCount)
    let reply = {} //initialize empty object
    if (result.rowCount != 0) {
      reply.db_data = result.rows
      response.status(200).send(reply)
    } else {
      reply.message = 'Dresses table is empty'
      reply.db_data = {}
      response.status(404).send(reply)
    }
  })
})

//for delete
app.delete('/contacts/:email', function (req, res) {
  let id_from_form = req.params.email
  if (id_from_form == '') {
    let reply = {}
    reply.message = 'Error ! id do not exist'
    reply.db_data = {}
    res.status(400).send(reply)
  } else {
    let reply = {}
    DB.connect()
    DB.query(
      'select * from contact where email=$1 ',
      [id_from_form],
      result => {
        if (result.rowCount != 0) {
          DB.query(
            'delete  from contact where email=$1 ',
            [id_from_form],
            result => {
              reply.message = 'contact deleted'
              res.status(200).send(reply)
            }
          )
        } else {
          reply.message = 'contact table does not have an email you enetered'
          reply.db_data = {}
          res.status(404).send(reply)
        }
      }
    )
  }
})

//for insert
app.post('/insert_contacts/', function (req, res) {
  let reply = {}
  DB.connect()
  DB.query(
    "insert into contacts values('" +
      req.body.firstName +
      "','" +
      req.body.image +
      "','" +
      req.body.lastName +
      "','" +
      req.body.email +
      "','" +
      req.body.phoneNo +
      "')",
    [],
    result => {
      reply.message = 'contacts inserted'
      res.status(200).send(reply)
    }
  )
})

//for update
app.put('/contacts/:email', function (req, res) {
  let id_from_form = req.params.email
  if (id_from_form == '') {
    let reply = {}
    reply.message = 'Error ! email do not exist'
    reply.db_data = {}
    res.status(400).send(reply)
  } else {
    let reply = {}
    DB.connect()
    DB.query('select * from contact where id=$1 ', [id_from_form], result => {
      if (result.rowCount != 0) {
        DB.query(
          "update contact set f_name='" +
            req.body.firstName +
            "',image='" +
            req.body.image +
            "',l_name='" +
            req.body.lastName +
            "',ph_no='" +
            req.body.phoneNo +
            "' where id=$1 ",
          [id_from_form],
          result => {
            reply.message = 'Contact updated'
            res.status(200).send(reply)
          }
        )
      } else {
        reply.message = 'Cntact table does not have an email you enetered'
        reply.db_data = {}
        res.status(404).send(reply)
      }
    })
  }
})
