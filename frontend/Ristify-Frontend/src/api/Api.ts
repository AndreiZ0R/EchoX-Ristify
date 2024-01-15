import axios, {AxiosRequestConfig, AxiosResponse} from "axios";
import {ListResponse, SingleResponse} from "../models/Response.ts";
import {Constants, Queries} from "../constants/constants.ts";

const base: string = "/api";
const usersEndpoint: string = `${base}/users`;
const authEndpoint: string = `${base}/auth`;

const buildAuthConfig = (): AxiosRequestConfig => {
    const token: string = localStorage.getItem(Queries.TOKEN) ?? "";
    return {
        headers: {
            [Constants.AUTHORIZATION_HEADER]: Constants.BUILD_AUTHORIZATION_HEADER(token),
        }
    };
}

const retrieveUsers = (): Promise<ListResponse> =>
    axios.get<ListResponse>(usersEndpoint, buildAuthConfig()).then((res: AxiosResponse<ListResponse>) => res.data);


const login = (username: string, password: string): Promise<SingleResponse> =>
    axios.post(`${authEndpoint}/login`, {
        username: username,
        password: password
    }).then((res: AxiosResponse<SingleResponse>): SingleResponse => res.data);

const register = (username: string, email: string, password: string, role: string, firstName: string,
                  lastName: string, country: string, birthDate: Date, createdAt: Date): Promise<SingleResponse> =>
    axios.post(`${authEndpoint}/register`, {
        username: username,
        email: email,
        password: password,
        role: role,
        firstName: firstName,
        lastName: lastName,
        country: country,
        birthDate: birthDate,
        createdAt: createdAt,
    }).then((res: AxiosResponse<SingleResponse>): SingleResponse => res.data);

export {retrieveUsers, login, register}