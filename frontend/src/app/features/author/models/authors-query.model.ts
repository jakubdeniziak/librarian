export interface AuthorsQuery {
  page: number;
  size: number;
  q?: string;
  sort?: string;
}

export const DEFAULT_AUTHORS_PAGE_SIZE = 25;
