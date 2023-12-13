interface Model {
}

interface BaseResponse {
    message: string,
    status: string,
}

interface SingleResponse extends BaseResponse {
    payload: Model,
}

interface ListResponse extends BaseResponse {
    payload: Model[],
}

type AppResponse = SingleResponse | ListResponse;

export type {SingleResponse, ListResponse, AppResponse, Model}