export interface AuthorInsights {
  myAuthorsCount: number;
  myBooksCount?: number;
  topCollectedAuthors?: Array<{
    id: string;
    firstName: string;
    lastName: string;
    collectedCount: number;
  }>;
}
