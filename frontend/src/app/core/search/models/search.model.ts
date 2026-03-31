export type SearchEntityType = 'book' | 'author' | 'publisher' | 'library';

export type BookFormat = 'AUDIOBOOK' | 'EBOOK' | 'HARDCOVER' | 'PAPERBACK' | 'OTHER';

export interface SearchRequest {
  query: string;

  includeBooks?: boolean;
  includeAuthors?: boolean;
  includePublishers?: boolean;
  includeLibraries?: boolean;

  libraryId?: string;
  language?: string;
  format?: BookFormat;
  yearFrom?: number;
  yearTo?: number;

  page?: number;
  size?: number;
}

export interface SearchHit {
  type: SearchEntityType;
  id: string;
  title: string;
  subtitle?: string;
  description?: string;
}

export interface SearchResponse {
  hits: SearchHit[];
  total: number;
}
