import axios, {AxiosRequestConfig, AxiosResponse} from "axios";
import {ListResponse, Model, SingleResponse} from "../models/Response.ts";
import {Constants, Queries} from "../constants/constants.ts";

const base: string = "/api";
const usersEndpoint: string = `${base}/users`;
const playlistsEndpoint: string = `${base}/playlists`;
const playlistSongsEndpoint: string = `${base}/playlistSongs`;
const authEndpoint: string = `${base}/auth`;

const buildAuthConfig = (): AxiosRequestConfig => {
    const token: string = localStorage.getItem(Queries.TOKEN) ?? "";
    return {
        headers: {
            [Constants.AUTHORIZATION_HEADER]: Constants.BUILD_AUTHORIZATION_HEADER(token),
        }
    };
}

const retrieveFunction = <T extends Model>(endpoint: string): Promise<T> =>
    axios.get<T>(endpoint, buildAuthConfig()).then((res: AxiosResponse<T>) => res.data);


//TODO: maybe generify this
// const retrieveUsers = (): Promise<ListResponse> =>
//     axios.get<ListResponse>(usersEndpoint, buildAuthConfig()).then((res: AxiosResponse<ListResponse>) => res.data);

const login = (username: string, password: string): Promise<SingleResponse> =>
    axios.post(`${authEndpoint}/login`, {
        username: username,
        password: password
    }).then((res: AxiosResponse<SingleResponse>): SingleResponse => res.data);

const retrieveUsers = (): Promise<ListResponse> => retrieveFunction<ListResponse>(usersEndpoint);
const retrievePlaylists = (): Promise<ListResponse> => retrieveFunction<ListResponse>(playlistsEndpoint);
const retrievePlaylistsWithSongs = (): Promise<ListResponse> => retrieveFunction<ListResponse>(playlistSongsEndpoint);

export {retrieveUsers, retrievePlaylists, retrievePlaylistsWithSongs, login}