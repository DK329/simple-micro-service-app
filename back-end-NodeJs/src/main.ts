import express, {json} from 'express';
import {router} from "./api/book-controller";

const app = express();


app.use(json());
app.use("/api/v1/books", router);
app.listen(8081,()=> console.log("Server has been started at 8081"));

