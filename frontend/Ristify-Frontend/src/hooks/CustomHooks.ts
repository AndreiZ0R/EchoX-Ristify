import {QueryClient, QueryFunction, useMutation, useQuery, useQueryClient, UseQueryResult} from "react-query";
import {Queries} from "../constants/constants.ts";
import {AppResponse, Model, SingleResponse} from "../models/Response.ts";
import {LoginModel} from "../models/User.ts";
import {login, register} from "../api/Api.ts";
import {useState} from "react";

type LoginUser = {
    username: string,
    password: string
}

type RegisterUser = {
    username: string,
    email: string,
    password: string,
    role: string,
    firstName: string,
    lastName: string,
    country: string,
    birthDate: Date,
    createdAt: Date,
}

type TransformerFunction = <T extends Model>(response: AppResponse) => T;

const defaultTransformer: TransformerFunction = <T extends Model>(response: AppResponse) => response.payload as T;

const useCustomQuery = <T extends Model>(
    key: string,
    queryFunction: QueryFunction<AppResponse>,
    enabledByDefault: boolean = true,
    transformFunction: TransformerFunction = defaultTransformer) => {
    const [enabledQuery, setEnabledQuery] = useState<boolean>(enabledByDefault);
    const query: UseQueryResult = useQuery<AppResponse, Error, T>({
        queryKey: key,
        queryFn: queryFunction,
        select: transformFunction,
        enabled: enabledQuery
    });

    const castedData: T = query.data as T;
    return {...query, setEnabledQuery, data: castedData};
}

const useLogin = () => {
    const queryClient: QueryClient = useQueryClient();
    return useMutation({
        mutationKey: Queries.LOGIN,
        mutationFn: ({username, password}: LoginUser) => login(username, password),
        onSuccess: (data: SingleResponse) => {
            const token: string = (data.payload as LoginModel).token;
            localStorage.setItem(Queries.TOKEN, token);

            return queryClient.invalidateQueries(Queries.USERS);
        }
    })
}

const useRegister = () => {
    return useMutation({
        mutationKey: Queries.REGISTER,
        mutationFn: ({username, email, password, role, firstName, lastName, country, birthDate, createdAt}: RegisterUser) => register(username,
            email, password, role, firstName, lastName, country, birthDate, createdAt),
        onSuccess: (data: SingleResponse) => {
            const token: string = (data.payload as LoginModel).token;
            localStorage.setItem(Queries.TOKEN, token);        }
    })
}

export {useCustomQuery, useLogin, useRegister}