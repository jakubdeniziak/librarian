export interface DashboardCounts {
  libraries: number;
  books: number;
  authors: number;
  publishers: number;
}

export interface GenreStat {
  genre: string;
  count: number;
}

export interface FormatBreakdownStat {
  format: string;
  count: number;
}

export interface TopAuthorStat {
  authorId: string;
  fullName: string;
  count: number;
}

export interface DashboardResponse {
  counts: DashboardCounts;
  topGenres?: GenreStat[];
  formatBreakdown?: FormatBreakdownStat[];
  topAuthors?: TopAuthorStat[];
}
