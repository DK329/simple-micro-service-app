import express from "express";
import mysql,{Pool} from "promise-mysql";
import dotenv from "dotenv";

export const router = express.Router();

let pool: Pool;
dotenv.config();
initPool();
async function initPool(){
    pool = await mysql.createPool({
        host:process.env.host,
        port:+process.env.port!,
        database:process.env.database,
        user:process.env.username,
        password:process.env.password,
        connectionLimit:+process.env.connection_limit!
    });
}
type Book = {
    isbn:string,
    title:string
}

router.delete("/:isbn",async (req,res)=>{
    const result = await pool.query("DELETE FROM book WHERE isbn=?",
        [req.params.isbn]);
    res.sendStatus(result.affectedRows ? 204 : 404);
});

router.patch("/:isbn",async (req,res)=>{
    const book = req.body as Book;
    book.isbn = req.params.isbn;

    if (!book.title.trim()){
        res.sendStatus(400);
        return;
    }

    const result = await pool.query("UPDATE book SET title=? WHERE isbn=?",
        [book.title, book.isbn]);
    res.sendStatus(result.affectedRows ? 204 : 404);
});