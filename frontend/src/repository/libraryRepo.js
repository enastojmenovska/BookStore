import axios from '../custom-axios/axios';

const LibraryService = {
    fetchBooks: () => {
        return axios.get("/books");
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name,category,author,availableCopies) =>{
        return axios.post("/books/add",{
            "name":name,
            "category": category,
            "authorId": author,
            "availableCopies": availableCopies
        });
    },
    editBook: (id,name,category,author,availableCopies) =>{
        return axios.put(`/books/edit/${id}`,{
            "name":name,
            "category": category,
            "authorId": author,
            "availableCopies": availableCopies
        });
    },
    markAsRentedBook: (id) => {
        return axios.get(`/books/rent/${id}`);
    },
    fetchAuthors:()=>{
        return axios.get("/authors");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    }
}

export default LibraryService;