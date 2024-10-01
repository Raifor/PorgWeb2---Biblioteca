import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {LivroDtoType} from "../../types/livro-dto.type";

@Injectable({
  providedIn: 'root'
})
export class LivroService {
  private apiUrl = 'http://localhost:8080/api/livros';

  constructor(private http: HttpClient) {}

  getAll(): Observable<LivroDtoType[]> {
    return this.http.get<LivroDtoType[]>(`${this.apiUrl}`);
  }

  getById(id: number): Observable<LivroDtoType> {
    return this.http.get<LivroDtoType>(`${this.apiUrl}/${id}`);
  }

  createOrUpdate(livro: LivroDtoType): Observable<LivroDtoType> {
    return this.http.post<LivroDtoType>(this.apiUrl, livro);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  emprestar(id: number): Observable<LivroDtoType> {
    return this.http.put<LivroDtoType>(`${this.apiUrl}/${id}/emprestar`, null);
  }

  devolver(id: number): Observable<LivroDtoType> {
    return this.http.put<LivroDtoType>(`${this.apiUrl}/${id}/devolver`, null);
  }
}
