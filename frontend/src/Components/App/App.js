import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import LibraryService from '../../repository/libraryRepo';
import Categories from '../Categories/categories';
import Authors from "../Authors/authors";
import Books from "../Books/BooksList/books";
import BookEdit from '../Books/BookEdit/bookEdit'
import BookAdd from "../Books/BookAdd/bookAdd";
import Header from '../Header/header';


class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            categories: [],
            books: [],
            authors : [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            <Route path={"/authors"} element={<Authors authors={this.state.authors}/>}/>
                            <Route path={"/categories"} element={<Categories categories={this.state.categories}/>}/>
                            <Route path={"/books"} element={<Books books={this.state.books}
                                                                   onDelete={this.deleteBook}
                                                                   onEdit={this.getBook}
                                                                   onMarkAsTaken={this.markAsTaken}/>}/>
                            <Route path={"/books/edit/:id"} element={<BookEdit book={this.state.selectedBook}
                                                                               categories={this.state.categories}
                                                                               authors={this.state.authors}
                                                                               onEditBook={this.editBook} />}/>
                            <Route path={"/books/add"} element={<BookAdd categories={this.state.categories}
                                                                         authors={this.state.authors}
                                                                         onAddBook={this.addBook}/>}/>
                        </Routes>
                    </div>
                </main>
            </Router>
        )
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadCategories();
        this.loadBooks();
        this.loadAuthors();
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            })
    }
    loadBooks() {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }
    loadAuthors() {
        LibraryService.fetchAuthors()
            .then((data)=>{
                this.setState({
                    authors: data.data
                })
            })
    }
    deleteBook= (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }
    getBook =(id)=>{
        LibraryService.getBook(id)
            .then((data)=>{
                this.setState({
                    selectedBook:data.data
                })
            })
    }
    editBook = (id,name,category,authorId,availableCopies) => {
        LibraryService.editBook(id,name,category,authorId,availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }
    addBook=(name,category,author,availableCopies)=> {
        LibraryService.addBook(name,category,author,availableCopies)
            .then(()=>{
                this.loadBooks()
            })
    }
    markAsTaken= (id) =>{
        LibraryService.markAsRentedBook(id)
            .then(() => {
                this.loadBooks();
            })
    }

}

export default App;
