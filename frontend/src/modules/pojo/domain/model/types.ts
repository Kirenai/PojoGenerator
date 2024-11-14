export type ActionResponse =
  | {
      success: true;
      zipUrl: string;
    }
  | { success: false; error: string };
