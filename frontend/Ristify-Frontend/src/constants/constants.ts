export const Constants = {
    AUTHORIZATION_HEADER: "Authorization",
    BUILD_AUTHORIZATION_HEADER: (token: string): string => `Bearer ${token}`
}

export const Queries = {
    USERS: "users",
    PLAYLISTS_WITH_SONGS: "playlistsWithSongs",
    LOGIN: "login",
    REGISTER: "register",
    TOKEN: "token"
};